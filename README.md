# SpringBoot-Test

# curl samples

## To get clients
curl -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjEiLCJyb2xlIjoiYWRtaW4iLCJpYXQiOjE3NDkwNjUwMTJ9.VvRXcKdXN3pMPiQ8QKzGzl-VWnqdz_4uEFQZFlgFO-Q" http://localhost:8080/clients

## To save clients
curl -X POST http://localhost:8080/clients \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjEiLCJyb2xlIjoiYWRtaW4iLCJpYXQiOjE3NDkwNjUwMTJ9.VvRXcKdXN3pMPiQ8QKzGzl-VWnqdz_4uEFQZFlgFO-Q" \
  -d '{"name": "João da Silva", "email": "joao@email.com", "phone": "11999999999"}'

## Save from csv file
curl -X POST http://localhost:8080/clients/upload \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjEiLCJyb2xlIjoiYWRtaW4iLCJpYXQiOjE3NDkwNjUwMTJ9.VvRXcKdXN3pMPiQ8QKzGzl-VWnqdz_4uEFQZFlgFO-Q" \
  -F "file=@clients.csv"

