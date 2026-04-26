using BLL.Services;
using Microsoft.AspNetCore.Mvc;

namespace MVCAppLayer.Controllers
{
    public class StudentController : Controller
    {
        StudentService studentService;
        public StudentController(StudentService studentService)
        {
            this.studentService = studentService;
        }
        public IActionResult Index()
        {
            return View();
        }

        public IActionResult Get()
        {
            //
            var data = studentService.Get();
            return View(data);
        }

        public IActionResult Create()
        {
            //
            var res = studentService.Create();
            return View();
        }

        public IActionResult Delete()
        {
            //
            var res = studentService.Delete();
            return View();
        }
    }
}
