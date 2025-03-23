import React, { ReactNode } from 'react';
import Container from '@mui/material/Container';
import CssBaseline from '@mui/material/CssBaseline';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import Header from './Header';
import Footer from './Footer';
import GitHubIcon from '@mui/icons-material/GitHub';
import LinkedInIcon from '@mui/icons-material/LinkedIn';
import { useTranslation } from 'react-i18next';

interface LayoutProps {
  children: ReactNode;
}

// TODO: Passen Sie theme nach Bedarf an
const defaultTheme = createTheme();

const Layout = ({ children }: LayoutProps) => {
  const { t } = useTranslation();

  const sections = [
    { title: t('layout.sections.home'), url: '/' },
    { title: t('layout.sections.aboutMe'), url: '/aboutMe' },
    { title: t('layout.sections.aboutThisWebsite'), url: '/aboutThisWebsite' },
    // { title: 'Tech Insights (Coming soon)', url: '/techInsights' },
    // { title: 'Latest Posts (Coming soon)', url: '/latestPosts' },
    // { title: 'Contact Me (Coming soon)', url: '/contactMe' },
    { title: t('layout.sections.imprint'), url: '/imprint' },
  ];
  
  let GitHub = {
    name: 'GitHub',
    icon: GitHubIcon,
    url: 'https://github.com/ceajay09'
  }
  
  let LinkedIn = {
    name: 'LinkedIn',
    icon: LinkedInIcon,
    url: 'https://linkedin.com/in/cesar-jaquiéry-9215aa179'
  }

  return (
    <ThemeProvider theme={defaultTheme}>
      <CssBaseline />
      <Container maxWidth="lg">
        <Header title={t('layout.headerTitle')} sections={sections} />
        <main>{children}</main>
        <Footer
          title={t('layout.footer.title')}
          description={t('layout.footer.description')}
        />
      </Container>
    </ThemeProvider>
  );
};

export default Layout;
