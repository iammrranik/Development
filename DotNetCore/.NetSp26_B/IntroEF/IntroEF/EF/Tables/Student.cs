using System;
using System.Collections.Generic;

namespace IntroEF.EF.Tables;

public partial class Student
{
    public int Id { get; set; }

    public byte[] Name { get; set; } = null!;

    public double Cgpa { get; set; }
}
