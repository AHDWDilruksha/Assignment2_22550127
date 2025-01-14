from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager

# Set up ChromeDriver automatically using WebDriver Manager
driver = webdriver.Chrome(ChromeDriverManager().install())

# Example: Open a website
driver.get("https://www.google.com")

# Close the browser after a few seconds
import time
time.sleep(5)  # Wait for 5 seconds
driver.quit()
