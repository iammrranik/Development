using Microsoft.AspNetCore.Components;
using System.ComponentModel.DataAnnotations;
using System.Reflection.Metadata.Ecma335;

namespace FormCProcessing.Models
{
    public class RegistrationModel
    {
        [Required]
        [StringLength(10)]
        public string Name { get; set; }
        [EmailAddress]
        [Required]
        public string Email { get; set; }
        [Required]
        public string Gender { get; set; }
        [Required]
        public string Department { get; set; }
        [Required]
        public string[] Hobbies { get; set; }
        [Range(1,40)]
        public int Roll { get; set; }
    }
}
