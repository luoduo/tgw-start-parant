--[[分布式锁,释放锁实现，如果key不存在则释放成功--]]

--[[参数--]]
local key_name = KEYS[1]
local key_value = ARGV[1]

--[[如果key已经不存在则,释放成功--]]
local exists = redis.call("EXISTS", key_name)
if 0 == exists then
    return 1
end

--[[如果key存在,比较当前值和传递过来的keyId是否一致
    一致删除key.
    不一致则不做任何操作,此时所可能已经被其它任务获取,也释放成功
--]]
local get_result = redis.call("GET", key_name)
if key_value == get_result then
    redis.call("DEL", key_name)
end

return 1
