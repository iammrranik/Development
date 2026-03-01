import math

a=10
b=3
sum=a+b
sub=a-b
mul=a*b
div=a/b
mod=a%b
pow(a,b)
power=a**b
floor_div=a//b
arr={a,b,sum, sub,mul,div,mod,pow(a,b),power,floor_div}
listt=[a,b,sum, sub,mul,div,mod,pow(a,b),power,floor_div]
tuplee=(a,b,sum, sub,mul,div,mod,pow(a,b),power,floor_div)

for i in arr:
    print(i, sep=', ', end=" ")
print()
for i in listt:
    print(i, sep=', ', end=" ")
print()
for i in tuplee:
    print(i, sep=", ", end=" ")
print()

a=5
b=5
if(a is b):
    print(f'equal {id(a)} ={id(b)}')
elif(a ==b):
    print(f"Not queal")
else:
    print('Nothing')

print(math.floor(3.49),math.ceil(3.49),int(3.8))

a=5
print(type(3.8), type(a))
a='aa'
print(type(3.8), type(a))

# print(type(input(a)))

for i in range(5):
    print(i, sep=', ', end=" ")
print()
for i in range(2,5):
    print(i, sep=', ', end=" ")
print()
for i in range(1,16,3):
    print(i, sep=', ', end=" ")
print()

print(abs(-55.99))
print(round(3.5))

sqrt= math.sqrt(16)
fact= math.factorial(5)
sin_val= math.sin(math.radians(45))
print(f"sqrt: {sqrt}, facrotial: {fact}, Sin 30: {sin_val}")

sent='anik, anika, morjina'
sent=sent.split(",")
print(sent)


s = "Python"
print(s[0])   # P
print(s[-2])  # n (শেষ অক্ষর)

sent=(" ").join(sent)
print(sent)

i=1
while i<=5:
    print(i, sep='. ', end=", ")
    i+=1

match a:
    case 1:
        print("One")
    case 5:
        print("Five")
    case _:
        print("Other")
        
