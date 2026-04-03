namespace No1_Task1_Pro.Models
{
    public class StudentModel
    {
        internal int Id { get; set; }

        internal string Name { get; set; }
        internal int Age { get; set; }
        internal string Department { get; set; }
        internal float CGPA { get; set; }

        public StudentModel() { }

        public StudentModel(int id, string name, int age, string department, float cgpa)
        {
            this.Id = id;
            this.Name = name;
            this.Age = age;
            this.Department = department;
            this.CGPA = cgpa;
        }


    }
}
