# BSTapp

BSTapp is a Spring Boot project I made that builds binary search trees (BSTs) from a bunch of numbers you type in. It spits out a JSON of the tree, It also remembers past trees in the database so you can check them out later.

## Features

- **User Input:**  
  The page at `/enter-numbers` where you can type a list of numbers (just separate them with commas) to build your BST.

- **Tree Builder:**  
  After you hit submit, it takes those numbers and makes a tree by inserting them one by one. You can also choose to make it balanced if you check the box.  
  It turns the tree into some formatted JSON and saves everything in an "in-memory" db.


- **History:**  
  `/previous-trees` page shows you all the old trees made with the numbers used.

- **Testing:**  
  inserting into the BST, making a balanced one, and converting it to JSON.

## Setup

1. **Clone the Repo:**

   ```bash
   git clone https://github.com/Elbaton1/BSTapp.git
   cd BSTapp
   ```

2. **Build + Run the App:**

   Use Maven to run everything:

   ```bash
   mvn clean package
   mvn spring-boot:run
   ```

3. **Open It in Your Browser:**

   - `http://localhost:8080/enter-numbers` – to enter numbers and build tree.
   - `http://localhost:8080/previous-trees` – to look at old trees.
   - `http://localhost:8080/h2-console` – to look at db (use `jdbc:h2:mem:bstdb`, username `sa`, and leave the password blank).

## How It Works

### Entering Numbers:
Go to `/enter-numbers`, type a list of numbers (like "5, 3, 8, 1, 4"), and tick the balanced checkbox if you want the tree to be balanced.

### What Happens Next:
When you hit submit, the `/process-numbers` route:

- Read your list of numbers.
- Build the tree (normal or balanced).
- Converts the whole thing to JSON.
- Saves in the H2 database.
- Takes you to a result page that shows the JSON


The `/previous-trees` route takes all past entries and shows them.

## What I Used

- **Spring Boot:** backend logic.
- **Thymeleaf:** HTML templates.
- **Spring Data JPA + H2 Database:** Saves numbers/trees in memory.
- **Maven:** Manages project and deps.
- **HTML + CSS:** Simple styles.css in static


