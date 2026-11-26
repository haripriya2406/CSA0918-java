# Task 04 – A4: Serialization Design (4 Marks)

Analyse the Hostel → Room → Resident object graph. Mark the live database Connection transient because it is a runtime resource. Do not serialize plaintext passwords; if authentication data must persist, store a secure password hash.

Explain how serialization traverses reachable objects and how non-serializable reachable fields can cause NotSerializableException unless handled. Explain serialVersionUID and what happens to a newly added phoneNumber field when reading an older stream with a compatible UID.

**Alternative:** Compare serialization with CSV/JSON for n residents. Discuss O(n) save/load work, file size, inspectability and schema evolution.

Evidence: transient decisions, object graph, versioning and text-format comparison.
