using Microsoft.AspNetCore.Mvc;
using Microsoft.Identity.Client;
using WebApplication6Pro_CRUD1.EF;
using WebApplication6Pro_CRUD1.EF.Tables;

namespace WebApplication6Pro_CRUD1.Controllers
{
    public class DepartmentController : Controller
    {
        StudentInformationContext db;
        
        public DepartmentController(StudentInformationContext db)
        {
            this.db = db;
        }

        public IActionResult Index()
        {
            var data = db.Departments.ToList();
            return View(data);
        }

        [HttpGet]
        public IActionResult Create()
        {
            return View();
        }

        [HttpPost]
        public IActionResult Create(Department d)
        {
            db.Departments.Add(d);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        public IActionResult Details(int id)
        {
            var data = (from d in db.Departments where d.Id == id select d).SingleOrDefault();
            return View(data);
        }

        [HttpGet]
        public IActionResult Remove()
        {
            return View();
        }

        [HttpPost]
        public IActionResult Remove(int id)
        {
            var data = db.Departments.Find(id);
            if (data != null)
            {
                db.Departments.Remove(data);
                db.SaveChanges();
            }
            return RedirectToAction("Index");
        }

    }
}
