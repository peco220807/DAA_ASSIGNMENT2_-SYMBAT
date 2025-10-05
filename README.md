# MinHeap

A MinHeap is a special type of binary heap where the smallest element is always at the top.

## Key Points
- Each parent node is smaller than its children.
- Useful for efficiently getting the minimum value.
- Implemented using an array for simplicity.

# MinHeap Sorting & Benchmarking

This project implements a MinHeap data structure with support for:
- Insertion and extraction of minimum elements.
- HeapSort algorithm using MinHeap.
- Performance tracking.
- Benchmarking on arrays of different sizes and input types.
- CSV export of benchmarking results.

## Features
- **Heap operations**: insert, extractMin, heapify up/down.
- **HeapSort**: sorts arrays using a MinHeap.
- **Benchmarking**:
  Tests for input sizes `10^2`, `10^3`, `10^4`, `10^5`.
  Input types: `random`, `sorted`, `reversed`.
  Here I made diagrams where you can see the difference between optimized code and regular code:
<img width="238" height="96" alt="image" src="https://github.com/user-attachments/assets/d8ff1a99-0ab5-49bd-bdc6-54b1cd86b364" />
<img width="270" height="97" alt="image" src="https://github.com/user-attachments/assets/f202ed84-31f1-4dc5-b1f3-72c8e38dd263" />
<img width="523" height="312" alt="image" src="https://github.com/user-attachments/assets/ce7d091a-0d3f-4709-87be-2229c0019f5b" />
<img width="528" height="309" alt="image" src="https://github.com/user-attachments/assets/2be6bdd8-4506-4c64-b43a-6286169f4798" />
<img width="528" height="308" alt="image" src="https://github.com/user-attachments/assets/9432dbef-d586-4bbc-a397-af72f766f70f" />
  
- **CSV Export**: Results are saved to `results.csv` for analysis.
