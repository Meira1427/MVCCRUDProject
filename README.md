#Task List - a MVC Crud Project
##Meira Pentermann
##Assignment
The goal here is to have you implement C.R.U.D. (Create, Read, Update, Delete), which is some of the most common behavior on the web. It represents the states of persistence that almost every application has (further reading for those interested). Usually CRUD is associated with a database, but we don't want you worrying about one of those, so we are just going to use an array or map for persistence, and save its contents to a file.

Your application should adhere to the MVC pattern. Benefits of the MVC pattern is similar to that of encapsulation. There is a separation of concerns between the individual elements that make up the model, view, and controller respectively. Changing code in one has no affect on the code in the others. Their interactions are discussed below.

##My Project
This is an individual application not a public wiki, so imagine that the user logs in. They have their own task list, which they can reorder, as well as add to, delete from, and organize categories.

##Technologies
We used Spring STS and Gradle to manage the project. I had most of the functionality in the TaskDAOFileImpl class and the TaskController.

##Future Features
I need to add the "filter by category" feature to the main page. And I would love to allow user to pick different themes to change colors.

##Stumbling Points
When transitioning to select.do, choosing the "select" button next to a task, I went down many rabbit holes trying to pass the current task to the controller. I eventually got help from Steve.TheYounger and used this strategy.
<a href="select.do?item=${task.item}"><input type="submit" value="Select"/></a>
Once I learned that strategy, passing the param along the href path, I could use it to link up the pictures and input buttons not part of a form.

##Stumbling JUNIT
This was not a test-driven project because I had trouble setting up my JUNIT tests. Just some dumb mistakes from copying over StateLab test files.
