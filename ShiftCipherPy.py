x = str(input("Enter a plain text: "))
choice = int(input("Enter 1 to return lower case or 2 to return upper case: "))
if(choice == 1):
    x = x.lower()
else:
    x = x.upper()
print(x)
n = int(input("Enter a shift factor: "))
y = list(x) # This is so that we can manipulate individual characters in the string

# Encryption
for i in range(len(y)):
    y[i] = chr(n + ord(y[i]))
print(str("".join(y)))

# Decryption
for i in range(len(y)):
    y[i] = chr(ord(y[i]) - n)
print(str("".join(y)))

# The reason why we enter the text lower case is because we get a wider range of character shifts
# As opposed to entering the text upper case where the range of character shifts is lower