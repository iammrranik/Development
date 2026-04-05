using System.Diagnostics;
using FormCProcessing.Models;
using Microsoft.AspNetCore.Mvc;

namespace FormCProcessing.Controllers
{
    public class HomeController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }
        [HttpGet]
        public IActionResult Login() {
            
            return View();
        }
        [HttpPost]
        public IActionResult Login(LoginModel obj) {
            if (ModelState.IsValid) { //validation
                return RedirectToAction("Index");
            }
            return View();
        }
        [HttpGet]
        public IActionResult Registration() {
            return View(new RegistrationModel());
        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        public IActionResult Registration(RegistrationModel obj) {
            if (ModelState.IsValid) {
                TempData["Msg"] = "Regisrtation Successful";
                return RedirectToAction("Login");
            }
            return View(obj);
        }
        //[HttpPost]
        //public IActionResult Login(string Uname, string Pass) {
        //    var name = Uname;
        //    var pass = Pass;
        //    return View();
        //}
        //[HttpPost]
        //public IActionResult Login(IFormCollection obj) {
        //    var name = obj["Uname"];
        //    var pass = obj["Pass"];
        //    return View();
        //}

        public IActionResult Privacy()
        {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}
