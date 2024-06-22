SELECT h.*, ROUND(o.price, 2) AS avg_nightly_price, ROUND(avg_nightly_price - h.price_per_night, 2) AS price_difference
FROM (SELECT hotel_id, AVG(order_amount / days_amount) AS price
      FROM bookings
      WHERE MONTH (check_in) = 5 AND MONTH (check_out) = 5 AND DAY (check_in) >= 1 AND DAY (check_out) <= 5
      GROUP BY hotel_id) o JOIN hotels h USING(hotel_id)

