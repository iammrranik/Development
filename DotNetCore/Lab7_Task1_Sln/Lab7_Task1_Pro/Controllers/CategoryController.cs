using Lab7_Task1_Pro.EF;
using Lab7_Task1_Pro.EF.Tables;
using Microsoft.AspNetCore.Mvc;

namespace Lab7_Task1_Pro.Controllers
{
    public class CategoryController : Controller
    {
        Lab7ProductsContext db;
        public CategoryController(Lab7ProductsContext db)
        {
            this.db = db;
        }

        public IActionResult Index()
        {
            return View();
        }

        [HttpGet]
        public IActionResult Create()
        {
            return View();
        }

        [HttpPost]
        public IActionResult Create(Category c)
        {
            db.Categories.Add(c); //query saved not committed
            db.SaveChanges(); //query commit returns no of rows affected
            TempData["Msg"] = c.Name + " Created Successfully";

            return RedirectToAction("Index");
        }

        public IActionResult Details(int id)
        {
            var data = (from c in db.Categories where c.Id == id select c).SingleOrDefault();
            return View(data);
            //db.Departments.Find(id); //Search primary key
        }

    }
}
