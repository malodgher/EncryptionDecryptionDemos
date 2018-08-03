def encrypt(ipt, k, s):
    xyz = ""
    lIpt = list(ipt)
    for i in range(len(lIpt)):
        get = lIpt[i]
        if(get.isalpha()):
            get = chr((k * (ord(get) + ord('A')) + s) % 26 + ord('A'))
        xyz += get
    return xyz


def decrypt(ipt, k_iv, s):
    xyz = ""
    lIpt = list(ipt)
    for i in range(len(lIpt)):
        get = lIpt[i]
        if(get.isalpha()):
            get = chr((k_iv * ((ord(get) + ord('A')) - s)) % 26 + ord('A'))
        xyz += get
    return xyz


def find_inverse(k):
    for d in range(1, 26):
        r = (d * k) % 26
        if r == 1:
            break
    else:
        raise ValueError("%d has no inverse mod 26" % (k))
    return d

x = str(input("Please enter the plain text: "))
x = x.upper()
w = int(input("Please enter the first number, a, of the key. NOTE: a cannot be an even number or any multiple of 13: "))
z = int(input("Please enter the second number, b, of the key: "))
e = encrypt(x,w,z)
e_x = decrypt(e, find_inverse(w), z)
print("\nInput: " + x)
print("Encrypted text is: " + e)
print("Decrypted text is: " + e_x)