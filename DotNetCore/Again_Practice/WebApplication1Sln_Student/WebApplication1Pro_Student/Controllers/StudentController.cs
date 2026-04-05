using Microsoft.AspNetCore.Mvc;
using WebApplication1Pro_Student.Models;

namespace WebApplication1Pro_Student.Controllers
{
    public class StudentController : Controller
    {
        public IActionResult Index()
        {
            //var student = new StudentModel(1, "X", 18, "IT", 3.99f);
            //return View(student);

            //StudentModel[] studentModels = new StudentModel[10];
            //for (int i = 0; i < 10; i++)
            //{
            //    studentModels[i] = new StudentModel(
            //            i,
            //            "Student" + i,
            //            random.Next(18, 30),
            //            random.Next(1, 4).ToString(),
            //            (float)random.Next(0, 4) + random.NextSingle()
            //    );
            //}
            //return View(studentModels);

            Random random = new Random();
            List<StudentModel> studentModels = new List<StudentModel>();

            for (int i = 0; i < 10; i++)
            {
                studentModels.Add
                (
                    new StudentModel
                    (
                        i,
                        "Student " + i,
                        random.Next(18, 30),
                        "Department " + random.Next(1, 4).ToString(),
                        (float)random.Next(0, 4) + random.NextSingle()
                    )
                );
            }
            return View(studentModels);
        }

        public IActionResult Details(int id)
        {
            Random random = new Random();
            StudentModel student = new StudentModel(
                id,
                "Student " + id,
                random.Next(18, 30),
                "Department " + random.Next(1, 4).ToString(),
                (float)random.Next(0, 4) + random.NextSingle()
            );
            return View(student);
        }

    }



}
