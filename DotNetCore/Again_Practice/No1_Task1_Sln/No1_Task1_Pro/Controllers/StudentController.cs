using Microsoft.AspNetCore.Mvc;
using No1_Task1_Pro.Models;

namespace No1_Task1_Pro.Controllers
{
    public class StudentController : Controller
    {
        public IActionResult Index()
        {
            Random random = new Random();
            List<StudentModel> students = new List<StudentModel>();
            for (int i = 1; i<=10; i++)
            {
                students.Add(new StudentModel
                    (
                        i,
                        "Name " + i.ToString(),
                        random.Next(18, 26),
                        "Department " + random.Next(1, 4),
                        random.Next(1, 4) + (float)Math.Round(random.NextDouble(),2)
                    )
                );
            }
            return View(students);
        }

        public IActionResult Profile(int id)
        {
            
            return View();
        }
    }
}
