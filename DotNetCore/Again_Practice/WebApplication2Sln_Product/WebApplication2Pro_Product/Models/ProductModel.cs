namespace WebApplication2Pro_Product.Models
{
    public class ProductModel
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public float Price { get; set; }
        public string Category { get; set; }

        public ProductModel(int id, string name, float price, string category)
        {
            Id = id;
            Name = name;
            Price = price;
            Category = category;
        }


    }
}
