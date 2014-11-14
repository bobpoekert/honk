(ns honk.util
  (import [java.util.concurrent BlockingQueue LinkedBlockingQueue]))

(defn queue-seq
  "Takes a queue and wraps it in a lazy seq that takes off the queue. The queue can be closed with close-queue!"
  [^BlockingQueue q]
  (lazy-seq
    (let [head (.take q)]
      (if (= head ::done)
        (do
          (.put q ::done)
          nil)
        (cons head (queue-seq q))))))

(defn close-queue!
  "Causes all queue-seqs taking from the given queue to terminate"
  [^BlockingQueue q]
  (.put q ::done))

(defrecord OAuthCred [consumer-key consumer-secret token secret])
