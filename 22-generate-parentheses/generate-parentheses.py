class Solution:
    def generateParenthesis(self, n):
        result = []

        def backtrack(current, open_count, close_count):
            # Agar string complete ho gayi
            if len(current) == 2 * n:
                result.append(current)
                return

            # Opening bracket laga sakte hain
            if open_count < n:
                backtrack(current + "(", open_count + 1, close_count)

            # Closing bracket tabhi laga sakte hain
            # jab opening brackets zyada hon
            if close_count < open_count:
                backtrack(current + ")", open_count, close_count + 1)

        backtrack("", 0, 0)

        return result