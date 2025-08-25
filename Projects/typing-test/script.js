document.addEventListener('DOMContentLoaded', () => {
    // DOM Elements
    const textDisplay = document.getElementById('text-display');
    const textInput = document.getElementById('text-input');
    const timerElement = document.getElementById('timer');
    const wpmElement = document.getElementById('wpm');
    const cpmElement = document.getElementById('cpm');
    const accuracyElement = document.getElementById('accuracy');
    const errorsElement = document.getElementById('errors');
    const restartBtn = document.getElementById('restart-btn');
    const difficultyBtns = document.querySelectorAll('.difficulty-btn');
    
    // Variables
    let timer = 60;
    let timerInterval = null;
    let characterTyped = 0;
    let errors = 0;
    let totalTyped = 0;
    let currentText = '';
    let isTyping = false;
    let currentDifficulty = 'easy';
    
    // Sample texts for different difficulty levels
    const texts = {
        easy: [
            "The quick brown fox jumps over the lazy dog. This pangram contains every letter of the English alphabet.",
            "Learning to type quickly and accurately is an essential skill in today's digital world.",
            "Practice makes perfect. The more you type, the better you will become at it.",
            "Typing is like playing a musical instrument. It requires rhythm and muscle memory.",
            "Focus on accuracy first, then speed. It's better to type slowly and correctly than quickly with errors."
        ],
        medium: [
            "The five boxing wizards jump quickly. Pack my box with five dozen liquor jugs. How vexingly quick daft zebras jump!",
            "Amazingly few discotheques provide jukeboxes. The job requires extra pluck and zeal from every young wage earner.",
            "We promptly judged antique ivory buckles for the next prize. Crazy Fredrick bought many very exquisite opal jewels.",
            "A quivering Texas zombie fought republic linked jewelry. Six big devils from Japan quickly forgot how to waltz.",
            "Sphinx of black quartz, judge my vow. The jay, pig, fox, zebra, and my wolves quack! Blowzy night-frumps vex'd Jack Q."
        ],
        hard: [
            "The exploration of space has captivated humanity's imagination for centuries. As we venture beyond our atmosphere, we discover new frontiers and expand our understanding of the universe.",
            "Quantum computing represents a paradigm shift in computational power. By harnessing quantum mechanical phenomena, these systems can solve complex problems exponentially faster than classical computers.",
            "The human brain contains approximately 86 billion neurons, forming trillions of neural connections. This intricate network gives rise to consciousness, enabling us to perceive, think, and experience emotions.",
            "Biodiversity is essential for maintaining ecological balance and resilience. Each species plays a unique role in its ecosystem, contributing to the complex web of interactions that sustain life on Earth.",
            "Artificial intelligence continues to evolve at an unprecedented pace, transforming industries and challenging our understanding of cognition. As these systems become more sophisticated, they raise profound questions about the future of work and human identity."
        ]
    };
    
    // Initialize the typing test
    function init() {
        resetTest();
        setEventListeners();
    }
    
    // Reset the typing test
    function resetTest() {
        clearInterval(timerInterval);
        timer = 60;
        characterTyped = 0;
        errors = 0;
        totalTyped = 0;
        isTyping = false;
        
        timerElement.textContent = timer;
        wpmElement.textContent = '0';
        cpmElement.textContent = '0';
        accuracyElement.textContent = '0%';
        errorsElement.textContent = '0';
        
        textInput.value = '';
        textInput.disabled = false;
        textInput.focus();
        
        // Generate new text
        generateText();
    }
    
    // Generate text for typing
    function generateText() {
        const textArray = texts[currentDifficulty];
        const randomIndex = Math.floor(Math.random() * textArray.length);
        currentText = textArray[randomIndex];
        
        // Create character spans for tracking
        textDisplay.innerHTML = '';
        currentText.split('').forEach(char => {
            const charSpan = document.createElement('span');
            charSpan.textContent = char;
            textDisplay.appendChild(charSpan);
        });
        
        // Highlight the first character
        textDisplay.firstChild.classList.add('active');
    }
    
    // Start the timer
    function startTimer() {
        timerInterval = setInterval(() => {
            timer--;
            timerElement.textContent = timer;
            
            // Update statistics while typing
            updateStatistics();
            
            if (timer <= 0) {
                finishTest();
            }
        }, 1000);
    }
    
    // Finish the typing test
    function finishTest() {
        clearInterval(timerInterval);
        textInput.disabled = true;
        isTyping = false;
        
        // Final statistics update
        updateStatistics();
    }
    
    // Update statistics (WPM, CPM, accuracy)
    function updateStatistics() {
        // Words per minute: (characters typed / 5) / time in minutes
        const timeElapsed = (60 - timer) / 60; // Convert to minutes
        const timeElapsedAdjusted = timeElapsed === 0 ? 1 : timeElapsed; // Avoid division by zero
        
        // WPM calculation (standard: 5 characters = 1 word)
        const wpm = Math.round((characterTyped / 5) / timeElapsedAdjusted);
        
        // CPM calculation
        const cpm = Math.round(characterTyped / timeElapsedAdjusted);
        
        // Accuracy calculation
        const accuracy = totalTyped === 0 ? 0 : Math.round(((totalTyped - errors) / totalTyped) * 100);
        
        // Update the display
        wpmElement.textContent = wpm;
        cpmElement.textContent = cpm;
        accuracyElement.textContent = `${accuracy}%`;
        errorsElement.textContent = errors;
    }
    
    // Process user input
    function processInput() {
        const inputValue = textInput.value;
        const currentChar = inputValue.length - 1;
        
        // Start timer on first character
        if (!isTyping && inputValue.length > 0) {
            isTyping = true;
            startTimer();
        }
        
        // Update character styling based on correctness
        const textCharacters = textDisplay.querySelectorAll('span');
        
        // Remove active class from all characters
        textCharacters.forEach(char => char.classList.remove('active'));
        
        // Process each character typed
        for (let i = 0; i < inputValue.length; i++) {
            totalTyped++;
            
            if (i < textCharacters.length) {
                if (inputValue[i] === textCharacters[i].textContent) {
                    textCharacters[i].classList.add('correct');
                    textCharacters[i].classList.remove('incorrect');
                    characterTyped++;
                } else {
                    textCharacters[i].classList.add('incorrect');
                    textCharacters[i].classList.remove('correct');
                    errors++;
                }
            }
        }
        
        // Add active class to current character
        if (currentChar + 1 < textCharacters.length) {
            textCharacters[currentChar + 1].classList.add('active');
        }
        
        // Check if the test is complete
        if (inputValue.length >= currentText.length) {
            finishTest();
        }
        
        // Update error count
        errorsElement.textContent = errors;
    }
    
    // Set difficulty level
    function setDifficulty(difficulty) {
        currentDifficulty = difficulty;
        
        // Update active button
        difficultyBtns.forEach(btn => {
            if (btn.dataset.difficulty === difficulty) {
                btn.classList.add('active');
            } else {
                btn.classList.remove('active');
            }
        });
        
        resetTest();
    }
    
    // Set event listeners
    function setEventListeners() {
        // Text input event
        textInput.addEventListener('input', processInput);
        
        // Restart button
        restartBtn.addEventListener('click', resetTest);
        
        // Difficulty buttons
        difficultyBtns.forEach(btn => {
            btn.addEventListener('click', () => {
                setDifficulty(btn.dataset.difficulty);
            });
        });
        
        // Prevent copy-paste
        textInput.addEventListener('paste', e => e.preventDefault());
        
        // Focus on text input when clicking on text display
        textDisplay.addEventListener('click', () => textInput.focus());
    }
    
    // Initialize the app
    init();
});