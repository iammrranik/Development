namespace WebApplication1Pro_Student.Models
{
    public class StudentModel
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public int Age { get; set; }
        public string Department { get; set; }
        public float Cgpa { get; set; }

        public StudentModel(int id, string name, int age, string department, float cgpa)
        {
            Id = id;
            Name = name;
            Age = age;
            Department = department;
            Cgpa = cgpa;
        }



    }
}
