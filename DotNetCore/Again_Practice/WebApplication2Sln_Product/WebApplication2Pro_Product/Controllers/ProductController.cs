using Microsoft.AspNetCore.Mvc;
using WebApplication2Pro_Product.Models;

namespace WebApplication2Pro_Product.Controllers
{
    public class ProductController : Controller
    {
        public IActionResult Index()
        {
            Random random = new Random();
            List<ProductModel> productModels = new List<ProductModel>();
            for (int i = 1; i<=10; i++)
            {
                productModels.Add(
                    new ProductModel(
                        i,
                        "Product " + i,
                        (float)random.Next(1, 2000) + random.NextSingle(),
                        "Category " + random.Next(1, 4)
                    )
                );
            }
            return View(productModels);
        }

        public IActionResult Details(int id)
        {
            Random random = new Random();
            ProductModel productModel = new ProductModel(id, "Product " + id, (float)random.Next(1, 2000) + random.NextSingle(), "Category " + random.Next(1, 4));
            return View(productModel);
        }
    }
}
