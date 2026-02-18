using Lab3_Task5_Pro.Models;
using Microsoft.AspNetCore.Mvc;

namespace Lab3_Task5_Pro.Controllers
{
    public class BookController : Controller
    {
        public IActionResult Index()
        {
            List<BookModel> bookModels = new List<BookModel>();
            bookModels.Add(new BookModel(1, "The Rust Programming Language", "Steve Klabnik", "Software Engineering"));
            bookModels.Add(new BookModel(2, "Mastering React", "Sarah Drasner", "Web Development"));
            bookModels.Add(new BookModel(3, "The Great Gatsby", "F. Scott Fitzgerald", "Classic Literature"));
            bookModels.Add(new BookModel(4, "Deep Learning with Python", "François Chollet", "Data Science"));
            bookModels.Add(new BookModel(5, "Atomic Habits", "James Clear", "Self-Help"));
            bookModels.Add(new BookModel(6, "The Silent Patient", "Alex Michaelides", "Thriller"));
            bookModels.Add(new BookModel(7, "Clean Code", "Robert C. Martin", "Software Engineering"));
            bookModels.Add(new BookModel(8, "Dune", "Frank Herbert", "Science Fiction"));
            bookModels.Add(new BookModel(9, "Python for Data Analysis", "Wes McKinney", "Data Science"));
            bookModels.Add(new BookModel(10, "The Pragmatic Programmer", "Andrew Hunt", "Software Engineering"));
            bookModels.Add(new BookModel(11, "Project Hail Mary", "Andy Weir", "Science Fiction"));
            bookModels.Add(new BookModel(12, "Algorithms Unlocked", "Thomas Cormen", "Software Engineering"));
            bookModels.Add(new BookModel(13, "The Hobbit", "J.R.R. Tolkien", "Fantasy"));
            bookModels.Add(new BookModel(14, "Foundations of Data Science", "Avrim Blum", "Data Science"));

            return View(bookModels);
        }

        public IActionResult List(string id)
        {
            List<BookModel> bookModels = new List<BookModel>();
            bookModels.Add(new BookModel(1, "The Rust Programming Language", "Steve Klabnik", "Software Engineering"));
            bookModels.Add(new BookModel(2, "Mastering React", "Sarah Drasner", "Web Development"));
            bookModels.Add(new BookModel(3, "The Great Gatsby", "F. Scott Fitzgerald", "Classic Literature"));
            bookModels.Add(new BookModel(4, "Deep Learning with Python", "François Chollet", "Data Science"));
            bookModels.Add(new BookModel(5, "Atomic Habits", "James Clear", "Self-Help"));
            bookModels.Add(new BookModel(6, "The Silent Patient", "Alex Michaelides", "Thriller"));
            bookModels.Add(new BookModel(7, "Clean Code", "Robert C. Martin", "Software Engineering"));
            bookModels.Add(new BookModel(8, "Dune", "Frank Herbert", "Science Fiction"));
            bookModels.Add(new BookModel(9, "Python for Data Analysis", "Wes McKinney", "Data Science"));
            bookModels.Add(new BookModel(10, "The Pragmatic Programmer", "Andrew Hunt", "Software Engineering"));
            bookModels.Add(new BookModel(11, "Project Hail Mary", "Andy Weir", "Science Fiction"));
            bookModels.Add(new BookModel(12, "Algorithms Unlocked", "Thomas Cormen", "Software Engineering"));
            bookModels.Add(new BookModel(13, "The Hobbit", "J.R.R. Tolkien", "Fantasy"));
            bookModels.Add(new BookModel(14, "Foundations of Data Science", "Avrim Blum", "Data Science"));

            if (string.IsNullOrEmpty(id))
            {
                return View(bookModels);
            }

            List<BookModel> filteredBookModels = new List<BookModel>();
            foreach (var book in bookModels)
            {
                if (id.Equals(book.Category))
                {
                    filteredBookModels.Add(book);
                }
            }

            return View(filteredBookModels);
        }
    }
}
