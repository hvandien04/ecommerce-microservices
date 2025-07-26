export default function Button({ children, className, ...props }) {
  return (
    <button
      className={`flex items-center justify-center px-4 py-2 font-medium text-white bg-blue-500 rounded-sm cursor-pointer hover:bg-blue-400 transition duration-300 ${className}`}
      {...props}
    >
      {children}
    </button>
  );
}