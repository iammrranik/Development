using System;
using System.Collections.Generic;

namespace WebApplication6Pro_CRUD1.EF.Tables;

public partial class Department
{
    public int Id { get; set; }

    public string? Name { get; set; }

    public virtual ICollection<Course> Courses { get; set; } = new List<Course>();
}
