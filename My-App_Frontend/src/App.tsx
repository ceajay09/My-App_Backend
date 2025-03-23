import React, { useState } from 'react';
import { Login } from "./authenticated/Login";
import { Register } from "./authenticated/Register";
import { Dashboard } from "./authenticated/Dashboard";
import Blog from './blog/Blog';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Imprint from './blog/Imprint';
import AboutMe from './blog/AboutMe';
import AboutThisWebsite from './blog/AboutThisWebsite';
import TechInsights from './blog/TechInsights';
import LatestPosts from './blog/LatestPosts';
import ContactMe from './blog/ContactMe';

const App: React.FC = () => {
  return (
    <div className="App">
      <Routes>
        <Route path="/blog" element={<Blog />} />
        <Route path="/" element={<Blog />} />
        <Route path="/imprint" element={<Imprint />} />
        <Route path="/aboutMe" element={<AboutMe />} />
        <Route path="/aboutThisWebsite" element={<AboutThisWebsite />} />
        <Route path="/techInsights" element={<TechInsights />} />
        <Route path="/latestPosts" element={<LatestPosts />} />
        <Route path="/contactMe" element={<ContactMe />} />
      </Routes>
    </div>
  );
};

export default App;

// import React, { useEffect, useState } from 'react';
// import { Routes, Route, Link } from 'react-router-dom';

// const Home = () => {
//   const [data, setData] = useState<any[]>([]);
//   const [error, setError] = useState('');

//   useEffect(() => {
//     fetch(`${import.meta.env.VITE_API_URL}/posts?_limit=3`)
//       .then((res) => res.json())
//       .then(setData)
//       .catch((err) => setError(err.message));
//   }, []);

//   return (
//     <div>
//       <h2>Home</h2>
//       {error && <p style={{ color: 'red' }}>Fehler: {error}</p>}
//       <ul>
//         {data.map((post) => (
//           <li key={post.id}>{post.title}</li>
//         ))}
//       </ul>
//     </div>
//   );
// };

// const About = () => <h2>About Page</h2>;

// export default function App() {
//   return (
//     <div>
//       <h1>Vite Test App</h1>
//       <nav>
//         <Link to="/">Home</Link> | <Link to="/about">About</Link>
//       </nav>

//       <Routes>
//         <Route path="/" element={<Home />} />
//         <Route path="/about" element={<About />} />
//       </Routes>
//     </div>
//   );
// }
