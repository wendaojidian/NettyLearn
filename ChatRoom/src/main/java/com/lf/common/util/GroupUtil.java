package com.lf.common.util;

import io.netty.channel.group.ChannelGroup;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/02/29
 */
public class GroupUtil {
    private static final Map<String, List<String>> GROUP_MAP = new ConcurrentHashMap<>();

    private static final Map<String, ChannelGroup> CHANNEL_GROUP_MAP = new ConcurrentHashMap<>();

    public static void createGroup(String groupId, List<String> userIdList) {
        GROUP_MAP.put(groupId, userIdList);
    }

    public static List<String> getMembers(String groupId) {
        return GROUP_MAP.get(groupId);
    }

    public static boolean joinGroup(String groupId, String userId) {
        List<String> userIdList = GROUP_MAP.get(groupId);
        if (null == userIdList) {
            return false;
        } else {
            userIdList.add(userId);
            CHANNEL_GROUP_MAP.get(groupId).add(SessionUtil.getChannel(userId));
            return true;
        }
    }

    public static boolean quitGroup(String groupId, String userId) {
        List<String> userIdList = GROUP_MAP.get(groupId);
        if (null == userIdList) {
            return false;
        } else {
            userIdList.remove(userId );
            if (userIdList.size() == 0) {
                GROUP_MAP.remove(groupId);
                CHANNEL_GROUP_MAP.get(groupId).remove(SessionUtil.getChannel(userId));
            }
            return true;
        }
    }

    public static void bindChannelGroup(String groupId, ChannelGroup channelGroup) {
        CHANNEL_GROUP_MAP.put(groupId, channelGroup);
    }

    public static ChannelGroup getChannelGroup(String groupId) {
        return CHANNEL_GROUP_MAP.get(groupId);
    }
}
