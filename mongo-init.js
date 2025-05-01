// Connect to the database
var db = db.getSiblingDB("orderinventorydb")

// Create collections
db.createCollection("inventory")
db.createCollection("orders")
db.createCollection("payments")
db.createCollection("customers")

// Create indexes
db.inventory.createIndex({ productId: 1 }, { unique: true })
db.orders.createIndex({ orderId: 1 }, { unique: true })
db.orders.createIndex({ customerId: 1 })
db.payments.createIndex({ paymentId: 1 }, { unique: true })
db.payments.createIndex({ orderId: 1 })
db.customers.createIndex({ customerId: 1 }, { unique: true })
db.customers.createIndex({ email: 1 }, { unique: true })

// Seed inventory data
db.inventory.insertMany([
  {
    productId: "PROD-001",
    name: "Laptop",
    description: "High-performance laptop with 16GB RAM",
    price: 1200.0,
    quantity: 50,
    category: "Electronics",
    createdAt: new Date(),
    updatedAt: new Date(),
  },
  {
    productId: "PROD-002",
    name: "Smartphone",
    description: "Latest model with 128GB storage",
    price: 800.0,
    quantity: 100,
    category: "Electronics",
    createdAt: new Date(),
    updatedAt: new Date(),
  },
  {
    productId: "PROD-003",
    name: "Office Chair",
    description: "Ergonomic office chair with lumbar support",
    price: 250.0,
    quantity: 30,
    category: "Furniture",
    createdAt: new Date(),
    updatedAt: new Date(),
  },
  {
    productId: "PROD-004",
    name: "Coffee Maker",
    description: "Automatic coffee maker with timer",
    price: 120.0,
    quantity: 45,
    category: "Appliances",
    createdAt: new Date(),
    updatedAt: new Date(),
  },
  {
    productId: "PROD-005",
    name: "Wireless Headphones",
    description: "Noise-cancelling wireless headphones",
    price: 180.0,
    quantity: 75,
    category: "Electronics",
    createdAt: new Date(),
    updatedAt: new Date(),
  },
])

// Seed customer data
db.customers.insertMany([
  {
    customerId: "CUST-001",
    name: "John Doe",
    email: "john.doe@example.com",
    phone: "555-123-4567",
    address: "123 Main St, Anytown, USA",
    createdAt: new Date(),
    updatedAt: new Date(),
  },
  {
    customerId: "CUST-002",
    name: "Jane Smith",
    email: "jane.smith@example.com",
    phone: "555-987-6543",
    address: "456 Oak Ave, Somewhere, USA",
    createdAt: new Date(),
    updatedAt: new Date(),
  },
  {
    customerId: "CUST-003",
    name: "Bob Johnson",
    email: "bob.johnson@example.com",
    phone: "555-555-5555",
    address: "789 Pine Rd, Nowhere, USA",
    createdAt: new Date(),
    updatedAt: new Date(),
  },
  {
    customerId: "CUST-004",
    name: "Vishwakant Singh",
    email: "vishwakant.singh@example.com",
    phone: "555-444-3333",
    address: "101 Tech Blvd, Innovation City, USA",
    createdAt: new Date(),
    updatedAt: new Date(),
  },
])

print("MongoDB initialization completed successfully")
