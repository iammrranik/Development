


using System.ComponentModel.DataAnnotations;

namespace FormCProcessing.Models
{
    public class LoginModel
    {
        [Required(ErrorMessage = "Provide Username")]
        [StringLength(10)]
        public string Uname { get; set; }
        [Required]
        [StringLength(20, MinimumLength = 8)]
        public string Pass { get; set; }
    }
}
