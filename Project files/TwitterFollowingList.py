import sys
from TwitterAPI import TwitterAPI

name1 = sys.argv[1]
name2 = sys.argv[2]

consumer_key = 'YOUR_CONSUMER_KEY'
consumer_secret = 'YOUR_CONSUMER_SECRET'
access_token_key = 'YOUR_ACCESS_TOKEN_KEY'
access_token_secret = 'YOUR_ACCESS_TOKEN_SECRET'

# Using OAuth1...
twitter = TwitterAPI(consumer_key,
                 consumer_secret,
                 access_token_key,
                 access_token_secret)

response  = twitter.request('friends/list', {'screen_name': name1, 'count':50})
followers = [follower for follower in response]

FollowingList = open('C:\\apache-tomcat-7.0.34\\webapps\\project\\FollowingList1.txt', 'w')

for user in followers:
    name = user['name']
    try:
        FollowingList.write("%s\n" % str(name))
    except UnicodeEncodeError:
        pass

FollowingList.close()


response  = twitter.request('friends/list', {'screen_name': name2, 'count':50})
followers = [follower for follower in response]

FollowingList = open('C:\\apache-tomcat-7.0.34\\webapps\\project\\FollowingList2.txt', 'w')

for user in followers:
    name = user['name']
    try:
        FollowingList.write("%s\n" % str(name))
    except UnicodeEncodeError:
        pass
    
FollowingList.close()