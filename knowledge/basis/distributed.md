## CAP
## Paxos
## Raft
Raft 算法是对 Paxos 算法的一种改进，它是一种更易理解的一致性算法。Raft 算法的核心思想是将一致性问题分解为三个相对独立的子问题：**领导选举、日志复制和安全性**。

<img src="/knowledge/assets/basis/raft.png" width="500px">

### 领导选举
* Leader 定时向所有 Followers 发送心跳包，如果 Follower 在各自随机超时时间内没有收到 Leader 的心跳包，就会发起选举。
* 发起选举的 Follower 会成为 Candidate，自身的 term 会增加 1，等待一个随机的时间之后 Candidate 会向其他节点发送 RequestVote 请求。
* 如果 Candidate 收到超过半数的投票，就会成为 Leader；如果没有 Candidate 收到超过半数的投票，就会重新发起选举。
### 日志复制
日志需要确保满足以下条件：
* Leader 不会覆盖或删除自己的日志，只会追加。
* 如果两个日志的 term 和 index 都相同，那么这两个日志就是相同的。
  * 由 Leader 唯一性保证。
* 如果两个日志相同，那么他们之前的所有日志都是相同的。
  * 写日志时会进行一致性检查，查看上一个日志是否一致，递归的保证了前面的所有日志一致。

### 安全性
需要保证以下条件：
* 已经 commit 过的日志必须存在于后续的 Leader 中。
* 对于没有 commit 的日志，可以丢弃。

Raft 通过以下机制保证安全性：
* Candidate 在发起选举时，会将自己的 term 和 index 传递给其他节点，如果其他节点的 term 和 index 比 Candidate 的大，就会拒绝投票。
  * 先比较 term，再比较 index。
  * 由于投票大于一半的节点才会成为 Leader，而 commit 的日志也需要大于一半的节点 commit，所以 Leader 一定会包含 commit 的日志（因为至少有一包含该日志的节点给他投票了）。
* Leader 只能提交当前 term 的日志，防止之前 commit 的日志被覆盖。
  * 如下图所示，c 时刻如果 S1 为 Leader 可以提交非当前 term 的日志 2 然后下线，在 d 时刻 S5 成为 Leader 时覆盖了之前的日志2，这样就会导致之前的 commit 日志被覆盖。

<img src="/knowledge/assets/basis/raft-commit.png" width="500px">

## ZAB
## Gossip

## 参考链接
* [Raft](https://juejin.cn/post/7218915344130359351)