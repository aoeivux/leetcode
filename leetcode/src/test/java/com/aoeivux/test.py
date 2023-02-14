import functools


def sort_rule(x, y):
    # print(x, y)

    a, b = x + y, y + x
    print(a, b)
    if a > b:
        return 1
    elif a < b:
        return -1
    else:
        return 0

nums = [10, 2, 11]
strs = [str(num) for num in nums]
strs.sort(key=functools.cmp_to_key(sort_rule))