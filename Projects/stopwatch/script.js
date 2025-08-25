document.addEventListener('DOMContentLoaded', () => {
    // DOM Elements
    const minutesElement = document.getElementById('minutes');
    const secondsElement = document.getElementById('seconds');
    const millisecondsElement = document.getElementById('milliseconds');
    const startButton = document.getElementById('start-btn');
    const stopButton = document.getElementById('stop-btn');
    const resetButton = document.getElementById('reset-btn');
    const lapButton = document.getElementById('lap-btn');
    const lapsList = document.getElementById('laps-list');
    
    // Variables
    let startTime;
    let elapsedTime = 0;
    let timerInterval;
    let lapCount = 0;
    
    // Format time to display
    function formatTime(time) {
        const minutes = Math.floor(time / 60000);
        const seconds = Math.floor((time % 60000) / 1000);
        const milliseconds = Math.floor((time % 1000) / 10);
        
        return {
            minutes: minutes.toString().padStart(2, '0'),
            seconds: seconds.toString().padStart(2, '0'),
            milliseconds: milliseconds.toString().padStart(2, '0')
        };
    }
    
    // Update display
    function updateDisplay() {
        const currentTime = Date.now() - startTime + elapsedTime;
        const formattedTime = formatTime(currentTime);
        
        minutesElement.textContent = formattedTime.minutes;
        secondsElement.textContent = formattedTime.seconds;
        millisecondsElement.textContent = formattedTime.milliseconds;
    }
    
    // Start timer
    function startTimer() {
        startButton.disabled = true;
        stopButton.disabled = false;
        resetButton.disabled = false;
        lapButton.disabled = false;
        
        startTime = Date.now();
        
        timerInterval = setInterval(updateDisplay, 10);
    }
    
    // Stop timer
    function stopTimer() {
        startButton.disabled = false;
        stopButton.disabled = true;
        
        clearInterval(timerInterval);
        elapsedTime += Date.now() - startTime;
    }
    
    // Reset timer
    function resetTimer() {
        startButton.disabled = false;
        stopButton.disabled = true;
        resetButton.disabled = true;
        lapButton.disabled = true;
        
        clearInterval(timerInterval);
        elapsedTime = 0;
        lapCount = 0;
        lapsList.innerHTML = '';
        
        minutesElement.textContent = '00';
        secondsElement.textContent = '00';
        millisecondsElement.textContent = '00';
    }
    
    // Record lap time
    function recordLap() {
        lapCount++;
        const currentTime = Date.now() - startTime + elapsedTime;
        const formattedTime = formatTime(currentTime);
        const lapTime = `${formattedTime.minutes}:${formattedTime.seconds}:${formattedTime.milliseconds}`;
        
        const lapItem = document.createElement('li');
        lapItem.innerHTML = `
            <span>Lap ${lapCount}</span>
            <span>${lapTime}</span>
        `;
        
        // Add animation class
        lapItem.classList.add('new-lap');
        
        // Add to the beginning of the list
        if (lapsList.firstChild) {
            lapsList.insertBefore(lapItem, lapsList.firstChild);
        } else {
            lapsList.appendChild(lapItem);
        }
        
        // Remove animation class after animation completes
        setTimeout(() => {
            lapItem.classList.remove('new-lap');
        }, 300);
    }
    
    // Event listeners
    startButton.addEventListener('click', startTimer);
    stopButton.addEventListener('click', stopTimer);
    resetButton.addEventListener('click', resetTimer);
    lapButton.addEventListener('click', recordLap);
    
    // Keyboard shortcuts
    document.addEventListener('keydown', (event) => {
        if (event.code === 'Space') {
            if (startButton.disabled) {
                stopTimer();
            } else {
                startTimer();
            }
        } else if (event.code === 'KeyR') {
            resetTimer();
        } else if (event.code === 'KeyL') {
            if (!lapButton.disabled) {
                recordLap();
            }
        }
    });
});