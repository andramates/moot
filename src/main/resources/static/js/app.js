let currentQuestion=null

async function loadQuestion(){

    const response = await fetch("/questions/today")
    const data = await response.json()

    if(!data){
        document.getElementById("questionText").innerText =
            "No question available today"
        return
    }

    currentQuestion = data
    document.getElementById("questionText").innerText = currentQuestion.text
}

async function vote(answer){

    await fetch(`/vote?questionId=${currentQuestion.id}&answer=${answer}`,{
        method:"POST"
    })

    window.location="/voted"

}

async function loadQuestionsForResults(){

    const response = await fetch("/questions")

    const questions = await response.json()

    const select = document.getElementById("questionSelect")

    select.innerHTML=""

    questions.forEach(q => {

        const option = document.createElement("option")

        option.value = q.id
        option.text = q.questionDate + " - " + q.text

        select.appendChild(option)

    })

}

async function getResultsByDate(){

    const id = document.getElementById("questionSelect").value

    const response = await fetch(`/vote/results/${id}`)

    const percentage =parseFloat(await response.text()).toFixed(2)

    document.getElementById("resultText").innerText =
        "yes: " + percentage + "%  |  no: " + (100 - percentage) + "%"

}

async function createQuestion(){
    const textInput = document.getElementById("questionTextInput");
    const dateInput = document.getElementById("questionDateInput");
    const button = document.querySelector(".card button");

    const text = textInput.value.trim();
    const date = dateInput.value;

    // invalid
    if(!text || !date){
        button.innerText = "Invalid question";
        button.style.background = "#ff4d4d";

        setTimeout(() => {
            button.innerText = "Create Question";
            button.style.background = "";
        }, 2000);

        return;
    }

    try {
        const response = await fetch("/questions", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                text: text,
                questionDate: date
            })
        });

        if(!response.ok) {
            button.innerText = "Question for this date already exists";
            button.style.background = "#ff4d4d";

            setTimeout(() => {
                button.innerText = "Create Question";
                button.style.background = "";
            }, 2000);

            return;
        }
        // success
        button.innerText = "Question added";
        button.style.background = "#28a745";

        // clear inputs
        textInput.value = "";
        dateInput.value = "";

        // reload dropdown
        loadQuestionsForResults();

    } catch (e) {
        button.innerText = "Error";
        button.style.background = "#ff4d4d";
    }

    // reset după 2 sec
    setTimeout(() => {
        button.innerText = "Create Question";
        button.style.background = "";
    }, 2000);
}