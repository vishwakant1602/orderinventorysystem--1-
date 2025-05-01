import { neon } from "@neondatabase/serverless"

// Create a SQL client
const sql = neon(process.env.DATABASE_URL)

export async function executeQuery(query, params = []) {
  try {
    console.log("Executing query:", query)
    console.log("With params:", params)

    const result = await sql(query, params)
    console.log("Query result:", result)

    return result
  } catch (error) {
    console.error("Database error:", error)
    throw error
  }
}
