class Solution:
    def removeInvalidParentheses(self, s):
        
        def is_valid(string):
            count = 0

            for ch in string:
                if ch == '(':
                    count += 1

                elif ch == ')':
                    count -= 1

                    if count < 0:
                        return False

            return count == 0

        current = {s}

        while True:

            valid = []

            for string in current:
                if is_valid(string):
                    valid.append(string)

            # Agar valid strings mil gayi,
            # to ye minimum removals wali strings hain
            if valid:
                return valid

            next_level = set()

            for string in current:

                for i in range(len(string)):

                    # Sirf parentheses remove karne hain
                    if string[i] not in '()':
                        continue

                    new_string = string[:i] + string[i + 1:]

                    next_level.add(new_string)

            current = next_level