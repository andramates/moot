function updateCountdown(){

    const now = new Date();

    const midnight = new Date();
    midnight.setHours(24,0,0,0);

    const diff = midnight - now;

    const hours = Math.floor(diff / (1000*60*60));
    const minutes = Math.floor((diff % (1000*60*60)) / (1000*60));
    const seconds = Math.floor((diff % (1000*60)) / 1000);

    const formatted =
        String(hours).padStart(2,"0") + ":" +
        String(minutes).padStart(2,"0") + ":" +
        String(seconds).padStart(2,"0");

    document.getElementById("countdown").innerText = formatted;
}

setInterval(updateCountdown,1000);
updateCountdown();