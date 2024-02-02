import { Link } from 'react-router-dom'
import styles from './Home.module.css'
import LinkButton from '../layouts/LinkButton';

function Home() {
  return (
    <section className={styles.home_container}>
      <h1>Bem-vindo à E-Sports <span>Ragnarok Legends</span></h1>
      <p>Gerencie aqui os seus times!</p>
      <p>Comece adicionando integrantes para a formação de times!!</p>
      <div className={styles.links_container}>
        <div className={styles.link_item}>
          <LinkButton to="/addintegrantes" text="Adicionar Integrantes" />
          <Link to="/addintegrantes"></Link>
        </div>
        <hr className={styles.divider} />
        <div className={styles.link_item}>
          <LinkButton to="/adddatas" text="Adicionar Datas" />
          <Link to="/adddatas"></Link>
        </div>
      </div>
    </section>
  );
}

export default Home
