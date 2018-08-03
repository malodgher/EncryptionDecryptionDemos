"""
Original function
def ex2(value, s):
    key = list(value)
    for i in range(len(key)):
        g = key[i]
        g = chr(ord(g) + s)
        if (g > 'z'):
            g = chr(ord(g) - 26)
        elif (g < 'a'):
            g = chr(ord(g) + 26)
        key[i] = g
    return str("".join(key))
"""

"""
def ex2(value, s):
    key = list(value)
    for i in range(len(key)):
        g = key[i]
        if(g == ' '):
            continue
        elif('A' <= g <= 'Z'):
            g = chr(ord(g) + s)
            if (g > 'Z'):
                g = chr(ord(g) - 26)
            elif (g < 'A'):
                g = chr(ord(g) + 26)
            key[i] = g
        elif('a' <= g <= 'z'):
            g = chr(ord(g) + s)
            if (g > 'z'):
                g = chr(ord(g) - 26)
            elif (g < 'a'):
                g = chr(ord(g) + 26)
            key[i] = g
    return str("".join(key))
"""

def ex2(value, s):
    key = list(value)
    for i in range(len(key)):
        g = key[i]
        if(g == ' '):
             continue
        elif('a' <= g <= 'z'):
            g = chr(ord(g) + (s % 26))
            if (not ('a' <= g <= 'z')):
                g = chr(ord(g) - 26)
            key[i] = g
        elif('A' <= g <= 'Z'):
             g = chr(ord(g) + (s % 26))
             if (not ('A' <= g <= 'Z')):
                 g = chr(ord(g) - 26)
             key[i] = g
    return str("".join(key))

x = str(input("Please enter plain text: "))
print(x)
y = int(input("Please enter key: "))
z = ex2(x,y)
print(z)
w = ex2(z, -y)
print(w)