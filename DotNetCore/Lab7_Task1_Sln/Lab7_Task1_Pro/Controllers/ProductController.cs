using Lab7_Task1_Pro.EF;
using Lab7_Task1_Pro.EF.Tables;
using Microsoft.AspNetCore.Mvc;

namespace Lab7_Task1_Pro.Controllers
{
    public class ProductController : Controller
    {
        Lab7ProductsContext db;
        public ProductController(Lab7ProductsContext db)
        {
            this.db = db;
        }
        public IActionResult Index()
        {
            var data = db.Products.ToList();
            return View(data);
        }

        [HttpGet]
        public IActionResult Create()
        {
            return View();
        }

        [HttpPost]
        public IActionResult Create(Product p)
        {
            db.Products.Add(p); //query saved not committed
            db.SaveChanges(); //query commit returns no of rows affected
            TempData["Msg"] = p.Name + " Created Successfully";

            return RedirectToAction("Index");
        }

        public IActionResult Details(int id)
        {
            var data = (from p in db.Products where p.Id == id select p).SingleOrDefault();
            return View(data);
            //db.Departments.Find(id); //Search primary key
        }


    }
}
