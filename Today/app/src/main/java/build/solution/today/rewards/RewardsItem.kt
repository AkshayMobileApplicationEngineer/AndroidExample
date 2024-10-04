package build.solution.today.rewards
//https://workholicpraveen.in/api/V34/result/reward.php
data class RewardsItem(
    val Coupon_id: String,
    val Prize: Int,
    val Quiz: String,
    val Rank: Int,
    val Reward_type: String,
    val Score: Int
)