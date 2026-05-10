Project: Online Bookstore(Full Stack Focused) 

Objective:Create a simple online bookstore where users can browse books, add them to their cart, and complete a checkout. 

Key Features: 
-Homepage: - Display a list of books (title, author, price).  
 - Search bar to find books by name.   
- Book Details Page: 
- Show book description and price.   
- Add to Cart button to store books in a cart.   
- Cart Page:  
 - List added books and total price.   
 - "Remove" button to delete items from the cart.  
 - Checkout Page:  
  - Simple order confirmation message after checkout.  
   - User Authentication (Optional):  
    - Simple login/signup system with basic validation. 

Tech Stack:: 
- Frontend: HTML, CSS, JavaScript  
 - Backend: Java, Spring, Spring Web MVC  
  - Optional:- Database: JDBC   
  - API Endpoints:  
   - `GET /books` → Fetch all books  
    - `GET /books/:id` → Fetch book details  
     - `POST /cart` → Add book to cart  
      - `GET /cart` → Get user's cart  
       - `DELETE /cart/:id` → Remove book from cart 
         - `POST /checkout` → Place an order

Learning Outcomes: - Understanding of  HTML, CSS, JavaScript  for UI design.   - Handling  Web MVC & Java for backend logic.   - Simple database management using JDBC(optional).   - Understanding basic REST API  concepts.   -Bonus (Optional Enhancements):- Add book reviews with comments.   - Display book images for better visuals. 
Follow the agents.md file.

for now implement the advanced frontend and strong backend codes(if required, check the agents.md).

use the specified tech stack only.

build backend in the way that i can run the project using .\mvnw.cmd spring-boot:run 

use the spring web mvc 

implement all the optional and bonus points.

do not add database for now.

focus and build the frontend(rich with css styles) and backend(that holds all features of the project)