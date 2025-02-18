package io.nozemi.runescape.net.message.game.command;

import io.nozemi.runescape.io.RSBuffer;
import io.nozemi.runescape.model.entity.Player;
import io.nozemi.runescape.model.item.Item;
import io.nozemi.runescape.net.message.game.Command;

public class LoyaltyRewards extends Command {

	private int dayReward, currentSpree, highestSpree, totalClaimedRewards;
	private Item[] loyaltyRewards;

	public LoyaltyRewards(int dayReward, int currentSpree, int highestSpree, int totalClaimedRewards, Item... loyaltyRewards) {
		this.dayReward = dayReward;
		this.currentSpree = currentSpree;
		this.highestSpree = highestSpree;
		this.totalClaimedRewards = totalClaimedRewards;
		this.loyaltyRewards = loyaltyRewards;
	}
	
	@Override
	protected RSBuffer encode(Player player) {
		RSBuffer buffer = new RSBuffer(player.channel().alloc().buffer(3 + 1 + 12 + (loyaltyRewards.length * 8))).packet(87).writeSize(RSBuffer.SizeType.SHORT);

		buffer.writeByte(dayReward);
		buffer.writeInt(currentSpree);
		buffer.writeInt(highestSpree);
		buffer.writeInt(totalClaimedRewards);

		for(Item reward : loyaltyRewards) {
			buffer.writeInt(reward.id());
			buffer.writeInt(reward.amount());
		}
		
		return buffer;
	}

}
