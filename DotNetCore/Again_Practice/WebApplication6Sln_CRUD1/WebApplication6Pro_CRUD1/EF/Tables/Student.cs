using System;
using System.Collections.Generic;

namespace WebApplication6Pro_CRUD1.EF.Tables;

public partial class Student
{
    public int Id { get; set; }

    public string Name { get; set; } = null!;

    public double? Cgpa { get; set; }
}
