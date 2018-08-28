def caesar_cipher()
	print "\nEnter plaintext: "
	plain_text = gets.chomp
	
	print "Enter numeric key: "
	key = gets.to_i
	
	puts "", "Encrypted plain text: #{mutbl_text = caesar_alg(plain_text, key)}"
	
	mutbl_text = caesar_alg(mutbl_text, (key*-1))
	puts "Decrypted encryption: #{mutbl_text}" if (mutbl_text == plain_text)
end

def caesar_alg(text, key)
	text = text.split(//)
	text.map! do |ch|
		# Used a regular expresion instead of traditional conditional statements
		if(not(/[[:alpha:]]/.match(ch)))
			ch
		elsif (/[[:lower:]]/.match(ch)) #(("a".ord <= ch.ord) and (ch.ord <= "z".ord))
			ch = (ch.ord + (key % 26)).chr
			(not(/[[:lower:]]/.match(ch))) ? (ch.ord - 26).chr : ch #(not (("a".ord <= ch.ord) and (ch.ord <= "z".ord))) ? (ch.ord - 26).chr : ch
		elsif (/[[:upper:]]/.match(ch)) #(("A".ord <= ch.ord) and (ch.ord <= "Z".ord))
			ch = (ch.ord + (key % 26)).chr
			(not(/[[:upper:]]/.match(ch))) ? (ch.ord - 26).chr : ch #(not (("A".ord <= ch.ord) and (ch.ord <= "Z".ord))) ? (ch.ord - 26).chr : ch
		end
	end
	
	return text.join("").to_s
end

def affine_cipher()
	print "\nEnter plaintext: "
	plain_text = gets.chomp.upcase
	
	print "Enter the first number, a, of the key. NOTE: a cannot be an even number or any multiple of 13: "
	a_key = gets.to_i
	
	print "enter the second number, b, of the key: "
	b_key = gets.to_i
	
	puts "", "Encrypted plain text: #{mutbl_text = affine_alg(plain_text, a_key, b_key, true)}"
	mutbl_text = affine_alg(mutbl_text, find_inverse(a_key), b_key, false)
	puts "Decrypted encryption: #{mutbl_text}" if (mutbl_text == plain_text)
	
end

def affine_alg(text, key_1, key_2, e_or_d)
	text = text.split(//)
	text.map! do |ch|
		(/[[:upper:]]/.match(ch)) ? 
			(e_or_d == true) ? ((key_1 * (ch.ord + "A".ord) + key_2) % 26 + "A".ord).chr : ((key_1 * ((ch.ord + "A".ord) - key_2)) % 26 + "A".ord).chr
		: ch
	end
	
	return text.join("").to_s
end

def find_inverse(key_1)
	(1..26).each do |d|
		return d if(((d * key_1) % 26) == 1) 
	end
	raise StandardError, "#{key_1} has no inverse mod 26"
end

def cipher_menu()
	puts "","Welcome to my Ruby Encrytion-Decryption program!",""
	puts "For encryption-decryption through Caesar Cipher, enter 1"
	puts "For encryption-decryption through Affine Cipher, enter 2"
	print "To exit the program, press any other key: "
	choice = gets.to_i
	
	caesar_cipher() if (choice == 1)
	affine_cipher() if (choice == 2)
	exit if((choice != 1) and (choice != 2))
end

loop do
	cipher_menu()
end