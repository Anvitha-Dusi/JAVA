document.addEventListener('DOMContentLoaded', () => {
    // DOM Elements
    const previousOperandElement = document.getElementById('previous-operand');
    const currentOperandElement = document.getElementById('current-operand');
    const numberButtons = document.querySelectorAll('[data-number]');
    const operatorButtons = document.querySelectorAll('[data-action]');
    
    // Calculator state
    let currentOperand = '0';
    let previousOperand = '';
    let operation = undefined;
    let resetScreen = false;
    
    // Update display
    function updateDisplay() {
        currentOperandElement.textContent = formatNumber(currentOperand);
        
        if (operation != null) {
            previousOperandElement.textContent = `${formatNumber(previousOperand)} ${getOperationSymbol(operation)}`;
        } else {
            previousOperandElement.textContent = '';
        }
    }
    
    // Format number with commas for thousands
    function formatNumber(number) {
        const stringNumber = number.toString();
        const integerDigits = parseFloat(stringNumber.split('.')[0]);
        const decimalDigits = stringNumber.split('.')[1];
        
        let integerDisplay;
        
        if (isNaN(integerDigits)) {
            integerDisplay = '';
        } else {
            integerDisplay = integerDigits.toLocaleString('en', { maximumFractionDigits: 0 });
        }
        
        if (decimalDigits != null) {
            return `${integerDisplay}.${decimalDigits}`;
        } else {
            return integerDisplay;
        }
    }
    
    // Get operation symbol
    function getOperationSymbol(op) {
        switch(op) {
            case 'add': return '+';
            case 'subtract': return '-';
            case 'multiply': return '×';
            case 'divide': return '÷';
            default: return '';
        }
    }
    
    // Append number
    function appendNumber(number) {
        if (number === '.' && currentOperand.includes('.')) return;
        if (resetScreen) {
            currentOperand = '';
            resetScreen = false;
        }
        if (currentOperand === '0' && number !== '.') {
            currentOperand = number;
        } else {
            currentOperand += number;
        }
    }
    
    // Choose operation
    function chooseOperation(op) {
        if (currentOperand === '') return;
        if (previousOperand !== '') {
            calculate();
        }
        
        operation = op;
        previousOperand = currentOperand;
        currentOperand = '0';
        resetScreen = true;
    }
    
    // Calculate result
    function calculate() {
        let computation;
        const prev = parseFloat(previousOperand);
        const current = parseFloat(currentOperand);
        
        if (isNaN(prev) || isNaN(current)) return;
        
        switch (operation) {
            case 'add':
                computation = prev + current;
                break;
            case 'subtract':
                computation = prev - current;
                break;
            case 'multiply':
                computation = prev * current;
                break;
            case 'divide':
                if (current === 0) {
                    currentOperand = 'Error';
                    previousOperand = '';
                    operation = undefined;
                    updateDisplay();
                    return;
                }
                computation = prev / current;
                break;
            case 'percent':
                computation = prev * (current / 100);
                break;
            default:
                return;
        }
        
        currentOperand = computation.toString();
        operation = undefined;
        previousOperand = '';
        resetScreen = true;
    }
    
    // Clear calculator
    function clear() {
        currentOperand = '0';
        previousOperand = '';
        operation = undefined;
    }
    
    // Delete last digit
    function deleteDigit() {
        if (currentOperand === 'Error') {
            clear();
            updateDisplay();
            return;
        }
        
        if (currentOperand.length === 1) {
            currentOperand = '0';
        } else {
            currentOperand = currentOperand.slice(0, -1);
        }
    }
    
    // Handle percentage
    function handlePercent() {
        const current = parseFloat(currentOperand);
        if (isNaN(current)) return;
        currentOperand = (current / 100).toString();
    }
    
    // Event listeners for number buttons
    numberButtons.forEach(button => {
        button.addEventListener('click', () => {
            appendNumber(button.getAttribute('data-number'));
            updateDisplay();
        });
    });
    
    // Event listeners for operator buttons
    operatorButtons.forEach(button => {
        button.addEventListener('click', () => {
            const action = button.getAttribute('data-action');
            
            switch(action) {
                case 'add':
                case 'subtract':
                case 'multiply':
                case 'divide':
                    chooseOperation(action);
                    break;
                case 'calculate':
                    calculate();
                    break;
                case 'clear':
                    clear();
                    break;
                case 'delete':
                    deleteDigit();
                    break;
                case 'percent':
                    handlePercent();
                    break;
            }
            
            updateDisplay();
        });
    });
    
    // Keyboard support
    document.addEventListener('keydown', (event) => {
        if (/[0-9]/.test(event.key)) {
            appendNumber(event.key);
            updateDisplay();
        } else if (event.key === '.') {
            appendNumber('.');
            updateDisplay();
        } else if (event.key === '+') {
            chooseOperation('add');
            updateDisplay();
        } else if (event.key === '-') {
            chooseOperation('subtract');
            updateDisplay();
        } else if (event.key === '*') {
            chooseOperation('multiply');
            updateDisplay();
        } else if (event.key === '/') {
            event.preventDefault();
            chooseOperation('divide');
            updateDisplay();
        } else if (event.key === '%') {
            handlePercent();
            updateDisplay();
        } else if (event.key === 'Enter' || event.key === '=') {
            event.preventDefault();
            calculate();
            updateDisplay();
        } else if (event.key === 'Backspace') {
            deleteDigit();
            updateDisplay();
        } else if (event.key === 'Escape') {
            clear();
            updateDisplay();
        }
    });
    
    // Initialize display
    updateDisplay();
});