let currentQuestion=null

async function loadQuestion(){

    const response=await fetch("/questions/today")

    currentQuestion=await response.json()

    document.getElementById("questionText").innerText=currentQuestion.text

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

    const percentage = await response.text()

    document.getElementById("resultText").innerText =
        "YES votes: " + percentage + "%"

}