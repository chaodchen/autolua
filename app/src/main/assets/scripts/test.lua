---
--- Generated by EmmyLua(https://github.com/EmmyLua)
--- Created by v_chaodchen.
--- DateTime: 2023/6/15 14:48
---

function fibonacci(n)
    if n == 0 or n == 1 then
        return n
    else
        return fibonacci(n-1) + fibonacci(n-2)
    end
end


start_time = os.clock()
result = fibonacci(30)
end_time = os.clock()
Log:d("lua", "Result: " .. result)
Log:d("lua", "Time: " .. (end_time - start_time) .. "s")