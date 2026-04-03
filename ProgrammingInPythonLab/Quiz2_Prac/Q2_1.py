fruits = ["apple", "banana", "cherry", "date", "elderberry"]

print(fruits[4])

for i in range(len(fruits)):
    print(fruits[i], end=", ")
print()

print(fruits[-5])
print(fruits[1:4:2])
print(fruits[::-2])
print(fruits[::2])

for fruit in fruits:
    print(fruit, end=", ")
print()

print(dir(fruits))

print(help(fruits.append))

fruits.append("fig")
fruits.append("apple")
print(fruits)
fruits.remove("apple")
print(fruits)
fruits.append("grape")
print(fruits)
fruits.pop()
print(fruits)
fruits.insert(2, "blueberry")
print(fruits)
fruits.sort()
print(fruits)

print(fruits.index("banana"))
print(fruits.count("apple"))

print(fruits)

