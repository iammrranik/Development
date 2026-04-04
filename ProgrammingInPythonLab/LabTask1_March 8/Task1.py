mark = int(input("Enter your mark: "))

if mark%5==1 or mark%5==2:
    mark = mark + (5-(mark%5))
print("Your mark is: ", mark)


if mark%5>=3 and mark>=48 and mark<=90:
    print("Your mark is: ", mark+(5-(mark%5)))
else:
    print("Your mark is: ", mark)