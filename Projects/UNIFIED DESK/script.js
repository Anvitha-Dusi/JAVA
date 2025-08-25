document.addEventListener('DOMContentLoaded', function() {
    // Mode Toggle Functionality
    const workModeBtn = document.getElementById('work-mode');
    const personalModeBtn = document.getElementById('personal-mode');
    const workDashboard = document.querySelector('.work-dashboard');
    const personalDashboard = document.querySelector('.personal-dashboard');

    workModeBtn.addEventListener('click', function() {
        // Toggle active class on buttons
        workModeBtn.classList.add('active');
        personalModeBtn.classList.remove('active');
        
        // Show work dashboard, hide personal dashboard
        workDashboard.classList.add('active');
        personalDashboard.classList.remove('active');
        
        // Update document title to reflect current mode
        document.title = 'Work Mode | Unified Desk';
        
        // Simulate loading new data
        simulateLoading();
    });

    personalModeBtn.addEventListener('click', function() {
        // Toggle active class on buttons
        personalModeBtn.classList.add('active');
        workModeBtn.classList.remove('active');
        
        // Show personal dashboard, hide work dashboard
        personalDashboard.classList.add('active');
        workDashboard.classList.remove('active');
        
        // Update document title to reflect current mode
        document.title = 'Personal Mode | Unified Desk';
        
        // Simulate loading new data
        simulateLoading();
    });

    // Widget Controls Functionality
    const refreshButtons = document.querySelectorAll('.refresh');
    const minimizeButtons = document.querySelectorAll('.minimize');

    refreshButtons.forEach(button => {
        button.addEventListener('click', function() {
            const widget = this.closest('.widget');
            refreshWidget(widget);
        });
    });

    minimizeButtons.forEach(button => {
        button.addEventListener('click', function() {
            const widget = this.closest('.widget');
            const widgetContent = widget.querySelector('.widget-content');
            
            if (widgetContent.style.display === 'none') {
                widgetContent.style.display = 'block';
                this.innerHTML = '<i class="fas fa-minus"></i>';
            } else {
                widgetContent.style.display = 'none';
                this.innerHTML = '<i class="fas fa-plus"></i>';
            }
        });
    });

    // Add Reminder Functionality
    const addReminderBtn = document.querySelector('.add-reminder');
    if (addReminderBtn) {
        addReminderBtn.addEventListener('click', function() {
            const reminderText = prompt('Enter a new reminder:');
            if (reminderText && reminderText.trim() !== '') {
                addReminder(reminderText);
            }
        });
    }

    // Checkbox Functionality for Reminders
    const reminderCheckboxes = document.querySelectorAll('.reminder input[type="checkbox"]');
    reminderCheckboxes.forEach(checkbox => {
        checkbox.addEventListener('change', function() {
            const reminderLabel = this.nextElementSibling;
            if (this.checked) {
                reminderLabel.style.textDecoration = 'line-through';
                reminderLabel.style.color = '#7f8c8d';
                
                // Remove the completed reminder after 2 seconds
                setTimeout(() => {
                    this.closest('.reminder').style.opacity = '0';
                    setTimeout(() => {
                        this.closest('.reminder').remove();
                    }, 300);
                }, 2000);
            } else {
                reminderLabel.style.textDecoration = 'none';
                reminderLabel.style.color = '#2c3e50';
            }
        });
    });

    // Helper Functions
    function simulateLoading() {
        const widgets = document.querySelectorAll('.widget');
        widgets.forEach(widget => {
            const widgetContent = widget.querySelector('.widget-content');
            const originalContent = widgetContent.innerHTML;
            
            // Show loading state
            widgetContent.innerHTML = '<div class="loading">Loading data...</div>';
            
            // Simulate API call delay
            setTimeout(() => {
                widgetContent.innerHTML = originalContent;
            }, 800);
        });
    }

    function refreshWidget(widget) {
        const widgetContent = widget.querySelector('.widget-content');
        const originalContent = widgetContent.innerHTML;
        
        // Show loading state
        widgetContent.innerHTML = '<div class="loading">Refreshing data...</div>';
        
        // Simulate API call delay
        setTimeout(() => {
            widgetContent.innerHTML = originalContent;
            
            // Add a subtle animation to indicate fresh content
            widgetContent.style.animation = 'fadeIn 0.5s';
            setTimeout(() => {
                widgetContent.style.animation = '';
            }, 500);
        }, 800);
    }

    function addReminder(text) {
        const remindersContainer = document.querySelector('.reminders');
        const reminderCount = document.querySelectorAll('.reminder').length + 1;
        
        const newReminder = document.createElement('div');
        newReminder.className = 'reminder';
        newReminder.innerHTML = `
            <input type="checkbox" id="reminder${reminderCount}">
            <label for="reminder${reminderCount}">${text}</label>
        `;
        
        // Insert before the add button
        remindersContainer.insertBefore(newReminder, addReminderBtn);
        
        // Add event listener to the new checkbox
        const newCheckbox = newReminder.querySelector('input[type="checkbox"]');
        newCheckbox.addEventListener('change', function() {
            const reminderLabel = this.nextElementSibling;
            if (this.checked) {
                reminderLabel.style.textDecoration = 'line-through';
                reminderLabel.style.color = '#7f8c8d';
                
                // Remove the completed reminder after 2 seconds
                setTimeout(() => {
                    this.closest('.reminder').style.opacity = '0';
                    setTimeout(() => {
                        this.closest('.reminder').remove();
                    }, 300);
                }, 2000);
            } else {
                reminderLabel.style.textDecoration = 'none';
                reminderLabel.style.color = '#2c3e50';
            }
        });
        
        // Add a subtle animation for the new reminder
        newReminder.style.animation = 'fadeIn 0.5s';
    }

    // Add some CSS animations
    const style = document.createElement('style');
    style.textContent = `
        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }
        
        .loading {
            text-align: center;
            padding: 1rem;
            color: #7f8c8d;
        }
        
        .reminder {
            transition: opacity 0.3s ease;
        }
    `;
    document.head.appendChild(style);

    // Simulate notifications
    setTimeout(() => {
        simulateNewNotification();
    }, 5000);

    function simulateNewNotification() {
        // Only add notifications if work mode is active
        if (workModeBtn.classList.contains('active')) {
            const slackWidget = document.getElementById('slack-widget');
            const slackContent = slackWidget.querySelector('.widget-content');
            
            const newNotification = document.createElement('div');
            newNotification.className = 'notification';
            newNotification.innerHTML = `
                <span class="sender">Project Lead</span>
                <p>Can we discuss the latest feature implementation?</p>
                <span class="time">Just now</span>
            `;
            
            // Add the new notification at the top
            slackContent.insertBefore(newNotification, slackContent.firstChild);
            
            // Highlight the new notification
            newNotification.style.backgroundColor = '#e3f2fd';
            newNotification.style.animation = 'fadeIn 0.5s';
            
            // Return to normal after 3 seconds
            setTimeout(() => {
                newNotification.style.backgroundColor = '#f8f9fa';
            }, 3000);
        }
        
        // Schedule the next notification
        const delay = Math.floor(Math.random() * (20000 - 10000 + 1)) + 10000; // Random between 10-20 seconds
        setTimeout(simulateNewNotification, delay);
    }
});