
<img width="1500" height="100" alt="Screenshot 2025-12-18 at 8 39 38 PM" src="https://github.com/user-attachments/assets/d2dafc04-7a7f-46ce-a71e-3023834106b8" />


#  Sunset Grocery App, an E-Commerce API

# Objective:
This capstone focused on debugging, as well as using MySQL and insomnia to test. We had to modify an existing e-commerce application so rather than building from scratch, I worked within an unfamiliar codebase to identify and fix issues with product searching, product updates causing duplication, and UI constraints like maximum values on the website.
In addition to bug fixes, I made small improvements, such as updating the header color, to better understand how frontend styling connects to backend logic. This project strengthened my debugging skills, improved my confidence working with Spring Boot APIs, and gave me experience maintaining and improving real-world code.

# Personal Goals for This Capstone
This capstone was incredibly different from our past capstones. We were able to view the front and backend of a website/application. We did not have a lot of practice with Insomnia in class so I was excited to see first hand how the tests ran. This turned out to be my biggest challenge. My goal was to get more familiar with the testing process and act as a detective to figure out a solution. 

# Planning Process
For my Capstone 3, I planned my work by breaking the instructions into smaller, clearer steps instead of trying to do everything at once. Seeing the whole project at once felt overwhelming, so separating it into phases helped me understand what needed to happen first and what could come later.

I focused on one piece at a time when setting up the project, fixing bugs, then adding new features—which made the work feel more manageable and less stressful. Laying everything out visually also helped me stay organized and gave me confidence as I checked off each step. Overall, this approach made the project easier to understand and helped me move through it with more clarity and intention.

<img width="500" height="400" alt="Screenshot 2025-12-19 at 8 49 48 AM" src="https://github.com/user-attachments/assets/7b1fc748-ec3c-4b36-904a-bc621f2cb570" />

Above, I have attached a scan of my notebook. You can see that I broke up the large steps into smaller, manageable ones. This make it cleaer to see my objectives. (:



# Phase 1: Core Setup & Category Functionality
In Phase 1, I focused on understanding the existing codebase and completing the required category functionality. This included finishing the CategoriesController and implementing the MySqlCategoryDao to properly retrieve category data from the database.
I worked on connecting the controller layer to the DAO, ensuring that category endpoints returned accurate data and followed the existing application structure. This phase helped me get comfortable navigating the project, understanding how controllers interact with DAOs, and verifying functionality through API testing.  

<img width="500" height="200" alt="noticing patterns" src="https://github.com/user-attachments/assets/163a7b02-f0b1-455a-b518-9f6bdc157cc8" />

Filling out the controller and DAO came to feel almost second nature, I was noticing patterns from the product controller and mirroring them in the categories controller.

Phase 1 set the foundation for the rest of the capstone. Super important!!!


<img width="1074" height="347" alt="returning null" src="https://github.com/user-attachments/assets/007fcccc-274f-4944-90b9-5e34d46dcf16" />



# Key Features & Improvements
---Frontend Enhancement ✎𓂃 ---
One of the things that I found the most interesting was changing the color of the website header and price. I decided to make this change because it pushed me to understand how the header was being styled and rendered within the project. I wanted to know “how”, I wanted to know where the color was coming from, I also wanted to personalize the website to feel more like something that I did. Especially since the code for this project was given to us. At first, it wasn’t obvious where the color was coming from, which required me to trace through the HTML structure and CSS rules rather than making assumptions. This change helped me better understand the project’s styling structure, including how CSS classes, layout positioning, and shared styles affect l components like the header. It also reinforced how the tiniest frontend changes often require careful navigation through multiple files, especially in an existing codebase! This was VERY cool!


<img width="500" height="400" alt="changed the colors here" src="https://github.com/user-attachments/assets/17290de5-2856-442e-aa65-a6d5fda1b70b" />        


<img width="500" height="400" alt="changed the color of my logo" src="https://github.com/user-attachments/assets/65933fe1-bce9-4de9-9296-48a46c1e008d" />

In the pictures above, I am showing where I modified the color of my logo/header. Its straight forward when you know where to look!



# Bug 1: Product Search / Filter Issue
To explain the bug that was causing my product search and maximum issue, I'd like to break it up into three categories. What the problem was, the cause and solution.
# Problem:
 Although the search and filter values were being set correctly, the product results did not fully reflect the selected filters. Specifically, the maximum price filter was not being applied, which caused products outside the intended price range to appear in search results.
# Root Cause:
 After tracing the request from the frontend to the database layer, I realized that while the max price variable existed, it was not fully integrated into the MySQL ProductDao query. The SQL statement did not correctly account for the max price condition, and the prepared statement parameter count did not match the query’s placeholders.
# Solution:
 To resolve this, I updated the SQL query to explicitly include a max price condition that allowed optional filtering when no value was provided. I added the following logic to ensure the maximum price was properly applied:
 
<img width="500" height="400" alt="line 28" src="https://github.com/user-attachments/assets/1c320170-c0cb-48f7-9913-78b9a3ba1b0b" />

 


I then updated the prepared statement to correctly match all eight parameters required by the query, As you can see, once the SQL and prepared statement were aligned, the max price filter worked as expected and the search results became accurate :


<img width="500" height="400" alt="all my tests pass!" src="https://github.com/user-attachments/assets/564ac9c5-3fa9-4d90-897c-843533bea8ce" />



<img width="500" height="400" alt="maxPrice was not working" src="https://github.com/user-attachments/assets/d14fedfe-ea7b-4d92-ad98-92e1d7f72f7f" />





# Bug 2: Product Update Duplication Issue
To explain the bug that was causing my duplicate products, I'd like to break it up into three categories. What the problem was, the cause and solution. 
# Problem:
 When updating existing products, the application was creating duplicate product entries instead of modifying the original record. This caused repeated products to appear in the database and on the website. In my case, it was happening with the product Maple syrup and Jasmine rice. 
# Root Cause:
 After investigating the issue, I discovered that product updates were not correctly targeting existing records in the database. Instead of updating rows by their unique identifiers, new rows were being created, which led to duplicate products.
# Solution:
 To resolve this, I directly updated the affected records in MySQL by targeting each product’s unique product Id! Using UPDATE statements allowed me to correct duplicated entries and ensure that changes were applied to the intended products rather than creating new ones. I formatted and split up my queries to make it easier to spot the problem and for presentation purposes. To summarize, by updating records directly with WHERE product_id =I was able to eliminate duplicate entries and restore data integrity. 
 
<img width="500" height="400" alt="fixing duplicates" src="https://github.com/user-attachments/assets/fbf83d83-9dd6-4ed0-90e0-3ee2299a2338" />

 

The picture above shows the format that I chose to write my query!




# Overall Capstone Experience
This project helped me better understand how Spring Boot applications are structured, especially how controllers, services, and APIs work together to handle requests and return data. I also developed a stronger debugging mindset. Instead of guessing or changing things randomly, I learned to slow down, read error messages, trace data flow, and test small changes at a time. This made debugging feel more manageable and less overwhelming! I also felt less overwhelmed because I set aside time to plan, visually seeing my steps, helps IMMENSELY! 
Because the codebase already existed, I had to spend time reading and understanding someone else’s code before making changes. I said the code out loud to myself when I didn't understand it, that process taught me how to navigate unfamiliar files and follow logic across multiple classes
Overall, this project changed how I approach problems. I’m more patient, more intentional, and more confident in breaking issues down step by step. Rather than feeling stuck, I now see bugs as something I can work through with the right process.


# ✰✰✰✰HONORABLE MENTIONS/SOURCES ✰✰✰✰
-The instructions that were given to us, all three workbooks were a huge help to me. I had them pulled up at all times. 
-Workbook 9a was also a great help, W3 schools and my favorite place to get emojis (https://emojicombos.com/cute)
As always, my classmates are always so knowledgeable and have always made me feel welcome, seen and heard. Whether they are there as a second set of eyes, a teacher, emotional support or for motivation, they have always been there for me. Even for something as small as to stay up late in the zoom and keep me company.

Thank you to:

Hunbal, Muhammad, Mohammed, John, Shewitt, Margaret, Jaylen, Hignacio, Nisa and  Morgan 
# (˶ᵔ ᵕ ᵔ˶) ‹𝟹






